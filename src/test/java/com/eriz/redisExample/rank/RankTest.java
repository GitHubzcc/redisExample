package com.eriz.redisExample.rank;

import com.eriz.redisExample.Application;
import com.eriz.redisExample.Rank.service.RankService;
import com.eriz.redisExample.Rank.vo.Rank;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: eriz 471295986@qq.com
 * @date: 2019/7/14 14:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RankTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RankService rankService;

    private List<Rank> list = null;

    private final static String merchantRank = "merchant-rank";

    @Before
    public void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Rank(1L + i, 20.0 + i, "merchant" + i));
        }

    }

    @Test
    public void save() {
        System.out.println(rankService.save(list));
    }

    @Test
    public void get() {
        Set<String> opstion = rankService.range(merchantRank, 0, -1);
        Iterator<String> iterator = opstion.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
    }

    @Test
    public void remove() {
        System.out.println(rankService.remove(merchantRank, "1"));
    }

    @Test
    public void incrementScore() {
        System.out.println(rankService.incrementScore(merchantRank, "2", 2000.0));
    }

    @Test
    public void score() {
        System.out.println(rankService.score(merchantRank, "2"));
    }

    @Test
    public void rank() {
        System.out.println(rankService.rank(merchantRank, "2"));
    }

    @Test
    public void rangeWithScore() {
        Set<ZSetOperations.TypedTuple<String>> set = rankService.rangeWithScore(merchantRank, 0, -1);
        Iterator<ZSetOperations.TypedTuple<String>> stringIterator = set.iterator();
        while (stringIterator.hasNext()) {
            ZSetOperations.TypedTuple<String> zset = stringIterator.next();
            System.out.println("商家：" + zset.getValue() + "的排名是：" + zset.getScore());
        }
    }

    @Test
    public void revRange() {
        Set<String> set = rankService.revRange(merchantRank, 0, -1);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
    }

    @Test
    public void sortRange() {
        Set<String> set = rankService.sortRange(merchantRank, 0, -1);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
    }

    @Test
    public void size() {
        System.out.println(rankService.size(merchantRank));
    }

}
