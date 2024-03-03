package com.chansumeran.JobApp.job.impl;

import com.chansumeran.JobApp.job.Job;
import com.chansumeran.JobApp.job.JobRepository;
import com.chansumeran.JobApp.job.JobRequestDto;
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
    public void createJob(JobRequestDto jobRequestDto) {
        Job job = new Job();

        job.setTitle(jobRequestDto.getTitle());
        job.setDescription(jobRequestDto.getDescription());
        job.setMinSalary(jobRequestDto.getMinSalary());
        job.setMaxSalary(jobRequestDto.getMaxSalary());
        job.setLocation(jobRequestDto.getLocation());

        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateJob(Long id, JobRequestDto jobRequest) {
        Job jobToUpdate = jobRepository.findById(id).orElse(null);

        if (jobToUpdate != null) {

            jobToUpdate.setTitle(jobRequest.getTitle());
            jobToUpdate.setDescription(jobRequest.getDescription());
            jobToUpdate.setMinSalary(jobRequest.getMinSalary());
            jobToUpdate.setMaxSalary(jobRequest.getMaxSalary());
            jobToUpdate.setLocation(jobRequest.getLocation());

            jobRepository.save(jobToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public Boolean deleteJob(Long id) {

        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
