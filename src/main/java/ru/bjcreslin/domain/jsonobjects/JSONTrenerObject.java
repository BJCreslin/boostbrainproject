package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.ArrayList;

@Data
public class JSONTrenerObject {
    private float global_id;
    @JsonAlias("CoachCommentScore")
    private String coachCommentScore = null;
    @JsonAlias("Name")
    private String name;
    @JsonAlias("LastName")
    private String lastName;
    @JsonAlias("MiddleName")
    private String middleName;
    @JsonAlias("Gender")
    private String gender;
    @JsonAlias("DateOfBirth")
    private String dateOfBirth;
    @JsonAlias("Photo")
    private String photo = null;
    @JsonAlias("Citizenship")
    ArrayList<Object> citizenship = new ArrayList<Object>();
    @JsonAlias("PublicPhone")
    ArrayList<Object> publicPhone = new ArrayList<Object>();
    @JsonAlias("Email")
    ArrayList<Object> email = new ArrayList<Object>();
    @JsonAlias("AcademicDegree")
    ArrayList<Object> academicDegree = new ArrayList<Object>();
    @JsonAlias("OtherAchievements")
    private String otherAchievements = null;
    @JsonAlias("Sport")
    ArrayList<Object> sport = new ArrayList<Object>();
    @JsonAlias("Education")
    ArrayList<Object> education = new ArrayList<Object>();
    @JsonAlias("AdditionalEducation")
    ArrayList<Object> additionalEducation = new ArrayList<Object>();
    @JsonAlias("Attestation")
    ArrayList<Object> attestation = new ArrayList<Object>();
    @JsonAlias("JobOrganisation")
    ArrayList<Object> jobOrganisation = new ArrayList<Object>();
    @JsonAlias("CoachCategory")
    ArrayList<Object> coachCategory = new ArrayList<Object>();
    @JsonAlias("SportSpecialization")
    ArrayList<Object> sportSpecialization = new ArrayList<Object>();
    @JsonAlias("SportActivityGender")
    private String sportActivityGender;
    @JsonAlias("SportActivityMinAge")
    private float sportActivityMinAge;
    @JsonAlias("SportActivityMaxAge")
    private String sportActivityMaxAge = null;
    @JsonAlias("Services")
    ArrayList<Object> services = new ArrayList<Object>();
    @JsonAlias("PersonalAchievement")
    private String personalAchievement = null;
    @JsonAlias("Rank")
    ArrayList<Object> rank = new ArrayList<Object>();
    @JsonAlias("PupilAchievements")
    private String pupilAchievements = null;
    @JsonAlias("FederationMembership")
    ArrayList<Object> federationMembership = new ArrayList<Object>();
    @JsonAlias("CoachRankPosition")
    private String coachRankPosition = null;
    @JsonAlias("CoachRankTotal")
    private String coachRankTotal = null;
    @JsonAlias("CoachRankAverageScore")
    private String coachRankAverageScore = null;
    @JsonAlias("CoachComments")
    private String coachComments = null;
}


