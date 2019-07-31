package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "trenerTable")
public class JSONTrenerObject {
    @Id
    @JsonAlias("Global_id")
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
    ArrayList<Object> citizenship = new ArrayList<>();
    @JsonAlias("PublicPhone")
    ArrayList<Object> publicPhone = new ArrayList<>();
    @JsonAlias("Email")
    ArrayList<Object> email = new ArrayList<>();
    @JsonAlias("AcademicDegree")
    ArrayList<Object> academicDegree = new ArrayList<>();
    @JsonAlias("OtherAchievements")
    private String otherAchievements = null;
    @JsonAlias("Sport")
    ArrayList<Sport> sport = new ArrayList<>();
    @JsonAlias("Education")
    ArrayList<Object> education = new ArrayList<>();
    @JsonAlias("AdditionalEducation")
    ArrayList<Object> additionalEducation = new ArrayList<>();
    @JsonAlias("Attestation")
    ArrayList<Object> attestation = new ArrayList<>();
    @JsonAlias("JobOrganisation")
    ArrayList<Object> jobOrganisation = new ArrayList<>();
    @JsonAlias("CoachCategory")
    ArrayList<Object> coachCategory = new ArrayList<>();
    @JsonAlias("SportSpecialization")
    ArrayList<Object> sportSpecialization = new ArrayList<>();
    @JsonAlias("SportActivityGender")
    private String sportActivityGender;
    @JsonAlias("SportActivityMinAge")
    private float sportActivityMinAge;
    @JsonAlias("SportActivityMaxAge")
    private String sportActivityMaxAge = null;
    @JsonAlias("Services")
    ArrayList<Object> services = new ArrayList<>();
    @JsonAlias("PersonalAchievement")
    private String personalAchievement = null;
    @JsonAlias("Rank")
    ArrayList<Object> rank = new ArrayList<>();
    @JsonAlias("PupilAchievements")
    private String pupilAchievements = null;
    @JsonAlias("FederationMembership")
    ArrayList<Object> federationMembership = new ArrayList<>();
    @JsonAlias("CoachRankPosition")
    private String coachRankPosition = null;
    @JsonAlias("CoachRankTotal")
    private String coachRankTotal = null;
    @JsonAlias("CoachRankAverageScore")
    private String coachRankAverageScore = null;
    @JsonAlias("CoachComments")
    private String coachComments = null;
}


