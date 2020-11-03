package com.company;

public class Research {

    private String name;

    private Integer id;

    private Double aa1;
    private Double ab1;
    private Double bb1;
    private Double aa0;
    private Double ab0;
    private Double bb0;
    private Integer chrom;
    private Integer pos;

    public Research(String name, Integer id, Double aa1, Double ab1, Double bb1, Double aa0, Double ab0, Double bb0,Integer chrom, Integer pos){

        this.name=name;
        this.id=id;
        this.aa1=aa1;
        this.ab1=ab1;
        this.bb1=bb1;
        this.aa0=aa0;
        this.ab0=ab0;
        this.bb0=bb0;
        this.chrom=chrom;
        this.pos=pos;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Double getAa1(){
        return aa1;
    }

    public Double getAb1(){
        return ab1;
    }

    public Double getBb1(){
        return bb1;
    }

    public Double getAa0(){
        return aa0;
    }

    public Double getAb0(){
        return ab0;
    }

    public Double getBb0(){
        return bb0;
    }

    public Integer getchrom(){
        return chrom;
    }

    public Integer getpos(){
        return pos;
    }


}
