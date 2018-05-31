package org.zgl.logic.hall.ranking;


import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.User;
import org.zgl.player.PlayerInit;
import org.zgl.player.UserMap;
import org.zgl.utils.DateUtils;

import java.util.*;

public class RankingManager {
    private static RankingManager instance;
    private static List<UserMap> goldRanking = new ArrayList<>(20);
    private static List<UserMap> characterRanking = new ArrayList<>(20);
    private static List<RankingGoldPlayerDto> goldDto = new ArrayList<>(20);
    private static List<RankingCharactorPlayerDto> characterDto = new ArrayList<>(20);
    private static int updateTime;
    public static RankingManager getInstance() {
        if(instance == null)
            instance = new RankingManager();
        return instance;
    }

    private RankingManager() {
    }

    private void charatorRanking(){
        //获取所有用户
        Query query = QueryFactory.createQuery();
        String sql = "SELECT * FROM user";
        List<User> users = query.queryRows(sql,User.class,null);
        Map<String,UserMap> userMapMap = new HashMap<>(users.size());
        for(User u : users){
            userMapMap.putIfAbsent(u.getAccount(),PlayerInit.initUserMap(u));
        }
        //拼装消息以备排序
        List<RankingModel> goldModels = new ArrayList<>(users.size());
        List<RankingModel> characterModels = new ArrayList<>(users.size());
        for(UserMap um:userMapMap.values()){
            SQLWeathModel weath = um.getWeath();
            goldModels.add(new RankingModel(um.getAccount(),weath.getGold()));
            characterModels.add(new RankingModel(um.getAccount(),weath.getCharacterNum()));
        }
        //排序
        Collections.sort(goldModels);
        Collections.sort(characterModels);
        int size = users.size() >= 20 ? 20 : users.size();
        //取出最多前20名排行
        for(int i = 0;i<size;i++){
            goldRanking.add(userMapMap.get(goldModels.get(i).getAccount()));
            characterRanking.add(userMapMap.get(characterModels.get(i).getAccount()));
        }
        getGoldRanking();
        getCharacterRanking();
    }
    private void getGoldRanking() {
        goldDto = goldRanking(goldRanking);
    }
    private void getCharacterRanking() {
        characterDto = charatorRanking(characterRanking);
    }
    private List<RankingGoldPlayerDto> goldRanking(List<UserMap> userMaps){
        List<RankingGoldPlayerDto> loginDtos = new ArrayList<>(userMaps.size());
        for(UserMap um:userMaps){
            loginDtos.add(new RankingGoldPlayerDto().dto(um));
        }
        return loginDtos;
    }
    private List<RankingCharactorPlayerDto> charatorRanking(List<UserMap> userMaps){
        List<RankingCharactorPlayerDto> loginDtos = new ArrayList<>(userMaps.size());
        for(UserMap um:userMaps){
            loginDtos.add(new RankingCharactorPlayerDto().dto(um));
        }
        return loginDtos;
    }

    public RankingDto rankingList(){
        if(updateTime != DateUtils.currentDay()){
            charatorRanking();
            updateTime = DateUtils.currentDay();
        }
        return new RankingDto(goldDto,characterDto);
    }

    public static void main(String[] args) {
        RankingManager.getInstance().rankingList();
    }
}
