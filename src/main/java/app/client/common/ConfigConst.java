package app.client.common;

import app.client.user.session.UserSession;
import io.netty.util.AttributeKey;

public class ConfigConst {

	// 用户会话信息KEY
	public static final AttributeKey<UserSession> USER_SESSION = AttributeKey.valueOf("USER_SESSION");

	public static final String MD5_SIGN_SALT = "jxwaLsGWL9A";
	
	public static final String CONSUMER_REQUEST_THREAD_NAME = "requestQueueConsumer";
	
	public static final String CONSUMER_RESPONSE_THREAD_NAME = "reponseQueueConsumer";
	
	/**
    *  断线后协议队列处理检测间隔时间/毫秒
    */
	public static final long CHECK_SLEEP_INTERVEL = 200;
	 /**
    *  心跳包间隔/秒
    */
	public static final long TICK_PERIOD = 20;
	 /**
    * 定时断线重连时间/ms
    */
	public static long RECONNECT_PERIOD = 30000;
	/**
    * 断线重连次数
    */
	public static final byte RECONNECT_TIMES = 20; 
	/**
    * 空闲检测，读超时/秒
    */
	public static final short READ_IDLE_TIME = 25;
	/**
    * 空闲检测，读超时/秒
    */
	public static final short WRITE_IDLE_TIME = 0;
	/**
    * 空闲检测，读、写超时/秒
    */
	public static final short RW_IDLE_TIME = 0;
	
}
