package com.chansumeran.JobApp.job.impl;

import com.chansumeran.JobApp.job.Job;
import com.chansumeran.JobApp.job.JobRepository;
import com.chansumeran.JobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateJob(Long id, Job jobUpdate) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(jobUpdate.getTitle());
            job.setDescription(jobUpdate.getDescription());
            job.setMinSalary(jobUpdate.getMinSalary());
            job.setMaxSalary(jobUpdate.getMaxSalary());
            job.setLocation(jobUpdate.getLocation());

            jobRepository.save(job);
            return true;
        }

        return false;
    }

    @Override
    public Boolean deleteJob(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            jobRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
