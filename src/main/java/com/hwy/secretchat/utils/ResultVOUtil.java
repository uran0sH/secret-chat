package com.hwy.secretchat.utils;

import com.hwy.secretchat.model.vo.ResultVO;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-15
 * ResultVO工具类
 */
public class ResultVOUtil {

    /**
     * 成功结果
     * @param object Data对象
     * @return ResultVO
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    /**
     * 成功结果，Data对象为空
     * @return ResultVO
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 错误结果
     * @return ResultVO
     */
    public static ResultVO error(String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
