package app.client.net.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.client.common.Const;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.ResponseProtocol;
import app.client.net.task.misc.StatisticPrintTask;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月11日 上午10:32:43
 */
public class TaskManager {
	
	private static TaskManager instance = new TaskManager(); 
	
	private TaskManagerStatus status;
	
	private TaskManager(){
		
	}
	
	public void init(){
        // 初始化两条线程，一条消费请求对列，一条消费响应队列
		final LinkedBlockingQueue<RequestProtocol> requestQueueTmp = requestQueue;
		final LinkedBlockingQueue<ResponseProtocol> responseQueueTmp = responseQueue;
		Runnable requestRun = new Runnable() {
			@Override
			public void run() {
				for(;;){
					RequestProtocol request = null;
					try {
                        request = requestQueueTmp.take();
                        RequestTaskImpl task = new RequestTaskImpl(
                                request.getCtx(),
                                request);

                        requestThreadPool.submit(task);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		};
		Runnable responseRun = new Runnable() {
			@Override
			public void run() {
				for (;;) {
					ResponseProtocol response = null;
					try {
                        response = responseQueueTmp.take();
                        ResponseTaskImpl task = new ResponseTaskImpl(response);
                        responseThreadPool.submit(task);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread requestThread = new Thread(requestRun, Const.CONSUMER_REQUEST_THREAD_NAME);
		Thread responseThread = new Thread(responseRun, Const.CONSUMER_RESPONSE_THREAD_NAME);
		
		requestThread.start();
		responseThread.start();
		status = TaskManagerStatus.RUNNING;
	}

	public void initStatiscTask(){
		StatisticPrintTask task = new StatisticPrintTask();
		tickThreadPool.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
	}
	
	
	private LinkedBlockingQueue<RequestProtocol> requestQueue = new LinkedBlockingQueue<RequestProtocol>();

	private LinkedBlockingQueue<ResponseProtocol> responseQueue = new LinkedBlockingQueue<ResponseProtocol>();
	
	private ScheduledExecutorService requestThreadPool = Executors.newScheduledThreadPool(2);
	
	private ScheduledExecutorService responseThreadPool = Executors.newScheduledThreadPool(2);

	private ScheduledExecutorService tickThreadPool = Executors.newScheduledThreadPool(5);
	
	private ExecutorService miscThreadPool = Executors.newFixedThreadPool(20);

    /**
    * 把请求协议放入队列
    * @param request
    */
	public void addRequest2Queue(RequestProtocol request){
		if(status != TaskManagerStatus.SHUTDOWN){
			requestQueue.add(request);
		}
	}
	
    /**
    * 把响应协议放入队列
    */
	public void addResponse2Queue(ResponseProtocol response){
		if(status != TaskManagerStatus.SHUTDOWN){
			responseQueue.add(response);
		}
	}
	
    /**
    * 加入心跳任务
    */
	public Future<?> addTickTask(Runnable scheduleTask, long delay, long period, TimeUnit timeUnit){
		return tickThreadPool.scheduleAtFixedRate(scheduleTask, delay, period, timeUnit);
	}

	/**
	 * 加入延迟任务
	 */
	public Future<?> addDelayTask(Runnable scheduleTask, long delay, TimeUnit timeUnit){
		return tickThreadPool.schedule(scheduleTask, delay, timeUnit);
	}


	public void addMiscTask(Runnable scheduleTask){
		miscThreadPool.execute(scheduleTask);
	}

	public void shutDownMisc(){
		miscThreadPool.shutdown();
	}

	public void shutDown(){
		status = TaskManagerStatus.SHUTDOWN;
		requestThreadPool.shutdown();
		responseThreadPool.shutdown();
		tickThreadPool.shutdown();
	}
	
	public static TaskManager getInstance(){
		return instance;
	}
}

enum TaskManagerStatus{
	RUNNING,
	SHUTDOWN
}
