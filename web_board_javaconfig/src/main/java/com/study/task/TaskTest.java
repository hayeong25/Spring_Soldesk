package com.study.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
	
	/*
		Spring Scheduling
		@Scheduled(cron="") : CronTab 설정 방식과 같게
			"* * * * * * *" : 초 분 시 일 월 요일 년
			"0 0 * * * * *" : 매일 매시 정각
		@Scheduled(fixedDelay="") : 이전 Task의 종료 시간으로부터 정의된 시간만큼 지난 후에 Task 실행
		@Scheduled(fixedRate="") : 이전 Task의 시작 시간으로부터 정의된 시간만큼 지난 후에 Task 실행
	*/
	
//	@Scheduled(cron="0 * * * * *") // 매 분 0초마다 실행
//	public void schedulerTest() {
//		System.out.println("매 분 1초마다 스케쥴링...");
//	}
//	
//	@Scheduled(fixedDelay = 10000)
//	public void schedulerTest2() {
//		System.out.println("10초마다 스케쥴링...");
//	}
}