package org.zgl.logic.hall.ranking;


public class RankingModel implements Comparable<RankingModel> {
    private String account;
    private long resource;

    public RankingModel() {
    }

    public RankingModel(String account, long resource) {
        this.account = account;
        this.resource = resource;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getResource() {
        return resource;
    }

    public void setResource(long resource) {
        this.resource = resource;
    }

    @Override
    public int compareTo(RankingModel o) {
        //小到大排列
        return (int) (o.getResource() - resource);
    }

}
