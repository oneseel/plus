package com.plus.plus.common;

import com.plus.plus.post.repository.PostRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

  private final PostRepository postRepository;

  // 스케줄러가 동작하는 시간대에 따라 90일 이전의 데이터를 삭제
  // 90일 이유
  // 1. 데이터 유효성 : 일정 기간 이후에는 데이터가 더 이상 시스템이나 비즈니스 프로세스에 유용하지 않을 수 있다.
  // 2. 개인정보 보호 : 일부 규정 및 법률은 개인정보를 특정 기간 동안만 보유하도록 규정하고 있다.
  // 3. 데이베이스 성능 : 오래된 데이터를 유지하는 것은 데이터베이스 성능에 영향을 미칠 수 있다.
  // 4. 저장 공간 절약 : 90일 이후의 데이터를 삭제하면 저장 공간을 효과적으로 확보할 수 있습니다.
  // UTC 기준으로 하는 이유 : 다양한 지역에서 사용할 때 통일성을 유지한다.

  @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행 (UTC 기준)
  public void deletePostData() {
    LocalDateTime ninetyDaysAgo = LocalDateTime.now().minusDays(90);
    postRepository.deleteByModifiedDateBefore(ninetyDaysAgo);
  }
}
