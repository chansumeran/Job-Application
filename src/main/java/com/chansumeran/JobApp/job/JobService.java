package com.chansumeran.JobApp.job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createJob(JobRequestDto jobRequest);

    Job getJobById(Long id);

    Boolean updateJob(Long id, JobRequestDto jobRequest);

    Boolean deleteJob(Long id);
}
