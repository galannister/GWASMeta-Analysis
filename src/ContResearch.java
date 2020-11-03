package com.company;

public class ContResearch {
    private String name;

    private Integer id;

    private Double popaa;
    private Double popab;
    private Double popbb;
    private Double meanaa;
    private Double meanab;
    private Double meanbb;
    private Double stdDevaa;
    private Double stdDevab;
    private Double stdDevbb;
    private Integer chrom;
    private Integer pos;

    public ContResearch(String name, Integer id, Double popaa, Double popab, Double popbb, Double meanaa, Double meanab, Double meanbb,Double stdDevaa,Double stdDevab,Double stdDevbb, Integer chrom, Integer pos){

        this.name=name;
        this.id=id;
        this.popaa=popaa;
        this.popab=popab;
        this.popbb=popbb;
        this.meanaa=meanaa;
        this.meanab=meanab;
        this.meanbb=meanbb;
        this.stdDevaa=stdDevaa;
        this.stdDevab=stdDevab;
        this.stdDevbb=stdDevbb;
        this.chrom=chrom;
        this.pos=pos;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Double getPopaa(){
        return popaa;
    }

    public Double getPopab(){
        return popab;
    }

    public Double getPopbb(){
        return popbb;
    }

    public Double getMeanaa(){
        return meanaa;
    }

    public Double getMeanab(){
        return meanab;
    }

    public Double getMeanbb(){
        return meanbb;
    }

    public Double getStdDevaa(){
        return stdDevaa;
    }

    public Double getStdDevab(){
        return stdDevab;
    }

    public Double getStdDevbb(){
        return stdDevbb;
    }

    public Integer getchrom(){
        return chrom;
    }

    public Integer getpos(){
        return pos;
    }


}

