package ru.bjcreslin.domain.jsonobjects;

import lombok.Data;

import java.util.ArrayList;

@Data
public class JSONTrenerObject {
    private float global_id;
    private String CoachCommentScore = null;
    private String Name;
    private String LastName;
    private String MiddleName;
    private String Gender;
    private String DateOfBirth;
    private String Photo = null;
    ArrayList<Object> Citizenship = new ArrayList<Object>();
    ArrayList<Object> PublicPhone = new ArrayList<Object>();
    ArrayList<Object> Email = new ArrayList<Object>();
    ArrayList<Object> AcademicDegree = new ArrayList<Object>();
    private String OtherAchievements = null;
    ArrayList<Object> Sport = new ArrayList<Object>();
    ArrayList<Object> Education = new ArrayList<Object>();
    ArrayList<Object> AdditionalEducation = new ArrayList<Object>();
    ArrayList<Object> Attestation = new ArrayList<Object>();
    ArrayList<Object> JobOrganisation = new ArrayList<Object>();
    ArrayList<Object> CoachCategory = new ArrayList<Object>();
    ArrayList<Object> SportSpecialization = new ArrayList<Object>();
    private String SportActivityGender;
    private float SportActivityMinAge;
    private String SportActivityMaxAge = null;
    ArrayList<Object> Services = new ArrayList<Object>();
    private String PersonalAchievement = null;
    ArrayList<Object> Rank = new ArrayList<Object>();
    private String PupilAchievements = null;
    ArrayList<Object> FederationMembership = new ArrayList<Object>();
    private String CoachRankPosition = null;
    private String CoachRankTotal = null;
    private String CoachRankAverageScore = null;
    private String CoachComments = null;
}


