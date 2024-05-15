package com.openquartz.logserver.infrastructure.logrecord.diff;

import com.fastobject.diff.AbstractObjectDiff;
import com.google.common.collect.Lists;
import com.openquartz.logserver.pojo.Order;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @author svnee
 */
public class FastDiffUtil {

    public static void main(String[] args) throws Exception {
        Order order1 = getOrder1();
        Order order2 = getOrder2();
        //annotationDiffUtil(order1, order2);
        diffObject(order1, order2);
    }


    private static void diffObject(Order order1, Order order2) {
        DiffNode diffNode = ObjectDifferBuilder.buildDefault().compare(order1, order2);
        diffNode.visit(
            (diffNode1, visit) -> System.out.println(diffNode1.getPath() + " => " + diffNode1.getState() + "###" +
                    diffNode1.canonicalGet(order1) + "@@@@" + diffNode1.canonicalGet(order2)));
    }

    private static void annotationDiffUtil(Order order1, Order order2) throws Exception {
        String chineseDiffStr = AbstractObjectDiff.genChineseDiffStr(order1, order2);
        System.out.println(chineseDiffStr);
    }

    private static Order getOrder2() {
        Order.UserDO userDO2 = new Order.UserDO();
        userDO2.setUserId(99L);
        userDO2.setUserName("小明");

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setOrderNo("bb");
        order2.setCreateTime(DateUtils.addDays(new Date(), 1));
        order2.setCreator(userDO2);
        order2.setItems(Lists.newArrayList("111"));
        return order2;
    }

    private static Order getOrder1() {
        Order.UserDO userDO1 = new Order.UserDO();
        userDO1.setUserId(88L);
        userDO1.setUserName("小美");


        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setOrderNo("aa");
        order1.setCreateTime(new Date());
        order1.setCreator(userDO1);
        order1.setProductName("好好学习");
        order1.setItems(Lists.newArrayList("111", "222"));
        return order1;
    }
}
