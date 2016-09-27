package com.xiaoshu.zkserver_observer;


public abstract interface ConfigChangeListener {
	public abstract void configChanged(String paramString1, String paramString2);
}