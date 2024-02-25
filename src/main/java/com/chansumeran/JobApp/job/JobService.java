package com.chansumeran.JobApp.job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    Boolean updateJob(Long id, Job jobUpdate);

    Boolean deleteJob(Long id);
}
