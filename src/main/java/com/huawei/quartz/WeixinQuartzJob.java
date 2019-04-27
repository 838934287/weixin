package com.huawei.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class WeixinQuartzJob extends QuartzJobBean {
	private RefreshAccessTokenTask refreshAccessTokenTask;
	public void setRefreshAccessTokenTask(RefreshAccessTokenTask refreshAccessTokenTask) {
		this.refreshAccessTokenTask = refreshAccessTokenTask;
	}
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		refreshAccessTokenTask.refreshToken();
	}

}
