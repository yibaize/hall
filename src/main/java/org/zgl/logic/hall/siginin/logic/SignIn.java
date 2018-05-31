package org.zgl.logic.hall.siginin.logic;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.siginin.data.SignInDataTable;
import org.zgl.logic.hall.siginin.po.SQLSignInModel;
import org.zgl.logic.hall.vip.po.VipDataTable;
import org.zgl.logic.hall.weath.dto.WeathResourceDto;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.User;
import org.zgl.player.UserMap;
import org.zgl.utils.DateUtils;
import org.zgl.utils.builder_clazz.ann.Protocol;

@Protocol("16")
public class SignIn extends OperateCommandAbstract {
    public SignIn(String account) {
        super(account);
    }


    @Override
    public Object execute() {
        UserMap um = SessionManager.getSession(getAccount());
        SQLSignInModel model = um.getSignIn();
        if(model == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        int todayTime = DateUtils.currentDay();
        if(model.getSignInTime() >= todayTime)
            new GenaryAppError(AppErrorCode.AWARD_GET_ERR);
        int day = model.getSignDay();
        if(day >= 7)
            day = 0;
        day++;

        SignInDataTable dataTable = SignInDataTable.get(day);
        if(dataTable == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        SQLWeathModel weath = um.getWeath();
        //vip加成
        int vipSignIn = 0;
        VipDataTable vipDataTable = VipDataTable.get(weath.getVipLv());
        if(vipDataTable != null)
            vipSignIn = vipDataTable.getSingIn();
        long gold = dataTable.getGold();

        model.setSignDay(day);
        model.setSignInTime(todayTime);
        //更新数据
        User u = um.entity2map();
        Query query =QueryFactory.createQuery();
        query.update(u,new String[]{"signIn"});

        weath.addGoldOrDiamond(1,gold+vipSignIn*gold);
        weath.addGoldOrDiamond(2,dataTable.getDiamond());
        weath.update(um,true);
        return new WeathResourceDto(weath.getGold(),weath.getDiamond(),weath.getIntegral());
    }
}
