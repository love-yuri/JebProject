package cn.cslg.db;

import cn.cslg.model.Flower;

import java.util.ArrayList;
import java.util.List;

public class FlowerDB {
    public static List<Flower> flowerList = new ArrayList<>();

    static {
        flowerList.add(new Flower(1, "黄百合", 339, "想着  我们曾一起跨过海边，想着  我们曾一起晒太阳。想着  我们曾一起坐着小火车,想着  我们曾手牵手走过曾经。此刻，我的心情，一如阳光般温暖！", "img/huangbaihe.JPG", 100));
        flowerList.add(new Flower(2, "香水百合", 269, "美好升温，日子闪光", "img/xiangshuibaihe.JPG", 100));
        flowerList.add(new Flower(3, "红玫瑰", 68, "一心一意，初心不变", "img/hongmeigui.JPG", 100));
        flowerList.add(new Flower(4, "粉玫瑰", 269, "百合花开，幸福自来，手执玫瑰，挚爱此生。", "img/fenmeigui.JPG", 100));
        flowerList.add(new Flower(5, "紫玫瑰", 569, "为你动情一瞬，便想相爱一生。对你的思念太深，只想和你一直走下去，直到永远。", "img/zimeigui.JPG", 100));
        flowerList.add(new Flower(6, "黄玫瑰", 269, "这久违的感觉，那熟悉的气息。悠悠的，淡淡的，是那样的清甜，是那样的芳香。使我陶醉，令我痴狂。亦悬似真，如梦如幻。仿佛在诉说她的恬静与夜的清凉。", "img/huangmeigui.JPG", 100));
        flowerList.add(new Flower(7, "香槟玫瑰", 189, "元气满满，永远向着太阳", "img/xiangbinmeigui.JPG", 100));
        flowerList.add(new Flower(8, "粉色康乃馨", 166, "温柔的人，都是人间宝藏", "img/fensekangnaixin.JPG", 100));
    }
}
