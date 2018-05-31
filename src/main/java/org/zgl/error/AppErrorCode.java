package org.zgl.error;


import org.zgl.utils.builder_clazz.ann.ExcelInversionToAnn;
import org.zgl.utils.builder_clazz.ann.ExcelValue;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@ExcelInversionToAnn
public final class AppErrorCode{
    @ExcelValue(value = "账号在其他地方登陆")
    public final static int ACCOUNT_ERR = 401;
    @ExcelValue(value = "账号或密码错误")
    public final static int PASSWORD_ERR = 402;
    @ExcelValue(value = "账号已经登录")
    public final static int LOGIN_ERR = 403;
    @ExcelValue(value = "数据异常")
    public final static int DATA_ERR = 404;
    @ExcelValue(value = "登录失败")
    public final static int LOGIN_FAIL = 405;
    @ExcelValue(value = "服务器爆满...")
    public final static int SERVER_BE_PACKED = 406;
    @ExcelValue(value = "账号异常退出")
    public final static int CONNECTION_ERROR = 407;
    @ExcelValue(value = "账号尚未登陆无法进入房间")
    public final static int ACCOUNT_NOT_LOGIN_ERROR = 408;
    @ExcelValue(value = "服务器异常...")
    public final static int SERVER_ERR = 409;

    @ExcelValue(value = "您的vip等级不足")
    public final static int VIP_LV_ERR = 301;
    @ExcelValue(value = "还没达到领取奖励时间")
    public final static int TIMER_ERR = 302;
    @ExcelValue(value = "钻石不足，请先充值钻石")
    public final static int DIAMOND_ERR = 303;
    @ExcelValue(value = "金币不足，请先充值钻石")
    public final static int GOLD_NOT_ERR = 304;
    @ExcelValue(value = "您还没有购买摇钱树，请先购买摇钱树")
    public final static int TREE_NOT = 305;
    @ExcelValue(value = "今天已经签到")
    public final static int SIGNIN_ERR = 306;
    @ExcelValue(value = "当天奖励已经领取")
    public final static int AWARD_GET_ERR = 307;
    @ExcelValue(value = "您的积分不足")
    public final static int INTEGRAL_NOT_ERR = 308;
    @ExcelValue(value = "还没达到抽奖时间")
    public final static int ONLINE_AWARD_TIMER_ERR = 309;
    @ExcelValue(value = "今天的抽奖次数已经用完了哟，明天再来吧!")
    public final static int ONLINE_AWARD_NUM_ERR = 310;
    @ExcelValue(value = "亲，金币3万以上才能参与抽奖哟，请充值后再来!")
    public final static int DIAL_AWARD_NUM_ERR = 311;
    @ExcelValue(value = "当前还没购买该座驾，无法使用!")
    public final static int NOT_AUTO_ERR = 312;

    @ExcelValue(value = "您的好友数量已经达到上限升级vip等级")
    public final static int FRIEND_LIMIT_ERR = 501;
    @ExcelValue(value = "用户不存在")
    public final static int FRIEND_NOT_USER_ERR = 502;
    @ExcelValue(value = "该玩家已经是您的好友")
    public final static int FRIEND_IS_FRIEND_ERR = 503;
    @ExcelValue(value = "该玩家已经是您的仇家")
    public final static int FRIEND_IS_ENEMY_ERR = 504;


    @ExcelValue(value = "任务尚未完成")
    public final static int TASK_NOT_DONE = 604;

    @ExcelValue(value = "成功交换场景")
    public final static int EXCHANGE_SCENES_SUCCEED = 700;
}
