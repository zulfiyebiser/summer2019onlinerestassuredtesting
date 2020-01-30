package com.automation.pojos;
import com.google.gson.annotations.SerializedName;

public class Job {
    @SerializedName("job_id") //we have add this if variable name in java is different from variable name in JSON
    private String jobId;

    private String job_title;//otherwise, Gson will map them automatically
    private Integer min_salary;
    private Integer max_salary;

    public Job(){
    }

    public Job(String jobId, String job_title, Integer min_salary, Integer max_salary) {
        this.jobId = jobId;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Integer getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(Integer min_salary) {
        this.min_salary = min_salary;
    }

    public Integer getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(Integer max_salary) {
        this.max_salary = max_salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", job_title='" + job_title + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }
}