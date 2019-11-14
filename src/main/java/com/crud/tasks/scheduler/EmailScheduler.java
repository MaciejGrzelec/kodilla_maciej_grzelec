package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleMailService simpleMailService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;

    //@Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "*/10 * * * * *")
    public void sentInformationEmail(){
        String task;
        long size = taskRepository.count();
        if (size == 1L) {
            task = " task";
        } else { task = " tasks";}

        simpleMailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + task
        ));
    }
}
