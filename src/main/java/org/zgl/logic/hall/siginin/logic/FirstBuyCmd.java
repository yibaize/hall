package org.zgl.logic.hall.siginin.logic;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.shop.ShopEnum;
import org.zgl.logic.hall.siginin.data.FirstBuyAwardDataTable;
import org.zgl.logic.hall.siginin.data.FirstBuyDataTable;
import org.zgl.logic.hall.siginin.dto.FirstBuyAwardDto;
import org.zgl.logic.hall.siginin.dto.FirstBuyAwarksDto;
import org.zgl.logic.hall.siginin.po.SQLSignInModel;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.Db_user;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 首冲
 */
@Protocol("12")
public class FirstBuyCmd extends OperateCommandAbstract {

    public FirstBuyCmd(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(getAccount());
        SQLSignInModel signIn = userMap.getSignIn();
        if(signIn.isHasFirstBay())
            new GenaryAppError(AppErrorCode.AWARD_GET_ERR);
        SQLWeathModel weath = userMap.getWeath();
        int firstBayDay = signIn.getFirstBayDay()+1;
        FirstBuyDataTable dataTable = FirstBuyDataTable.get(firstBayDay);
        if(dataTable == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        List<FirstBuyAwardDataTable> list = dataTable.getAward();
        List<FirstBuyAwardDto> firstBayAwardDtos = new ArrayList<>(2);
        for(int i = 0;i<list.size();i++){
            FirstBuyAwardDataTable award = list.get(i);
            weath.addResource(ShopEnum.getEnum(award.getShopId()),award.getId(),award.getCount());
            firstBayAwardDtos.add(new FirstBuyAwardDto(award.getId(),award.getCount()));
        }
        weath.addIntegral(dataTable.getIntegral());
        signIn.setFirstBayDay(firstBayDay);
        signIn.setHasFirstBay(true);
        update(userMap,"weath");
        update(userMap,"signIn");
        return new FirstBuyAwarksDto(firstBayAwardDtos);
    }
    private void update(UserMap um,String field){
        Query query = QueryFactory.createQuery();
        Db_user user = um.entity2map();
        query.update(user,new String[]{field});
    }
}
