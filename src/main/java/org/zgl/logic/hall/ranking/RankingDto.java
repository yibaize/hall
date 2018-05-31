package org.zgl.logic.hall.ranking;

import org.zgl.utils.builder_clazz.ann.Protostuff;

import java.util.List;

@Protostuff
public class RankingDto {
    private List<RankingGoldPlayerDto> goldRanking;
    private List<RankingCharactorPlayerDto> characterRanking;

    public RankingDto() {
    }

    public RankingDto(List<RankingGoldPlayerDto> goldRanking, List<RankingCharactorPlayerDto> characterRanking) {
        this.goldRanking = goldRanking;
        this.characterRanking = characterRanking;
    }

    public List<RankingGoldPlayerDto> getGoldRanking() {
        return goldRanking;
    }

    public void setGoldRanking(List<RankingGoldPlayerDto> goldRanking) {
        this.goldRanking = goldRanking;
    }

    public List<RankingCharactorPlayerDto> getCharacterRanking() {
        return characterRanking;
    }

    public void setCharacterRanking(List<RankingCharactorPlayerDto> characterRanking) {
        this.characterRanking = characterRanking;
    }
}
