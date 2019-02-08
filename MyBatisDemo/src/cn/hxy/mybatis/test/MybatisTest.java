package cn.hxy.mybatis.test;

import java.io.IOException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cn.hxy.mybatis.datasource.*;
import cn.hxy.mybatis.po.Goods;

public class MybatisTest {

	public DataConnection dataConn = new DataConnection();
	private SqlSession sqlSession = null;

	@Before
	public void before() {
		try {
			sqlSession = dataConn.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		sqlSession.commit();
		sqlSession.close();
	}

	/**
	 * 
	 * Select all data from mysql.anqing database , android table;
	 * 
	 * @throws IOException
	 */
	@Test
	public void TestSelectAll() throws IOException {
		System.out.println("调试开始！");
//		SqlSession sqlSession=dataConn.getSqlSession();   
//        Goods user=sqlSession.selectOne("goodsMapper.selectUser",123);  
		List<Goods> goodsList = sqlSession.selectList("goodsMapper.selectAll");
		for (Goods good : goodsList) {
			System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", good.getNumber(), good.getName(), good.getPrice(),
					good.getPress(), good.getAuthor(), good.getLocation(), good.getDate(), good.getTime());
		}
//        sqlSession.close();
	}

	@Test
	public void TestSelect() throws IOException {

		Goods good = sqlSession.selectOne("goodsMapper.findGoodsByNumber", "6902083881085");
		if (good != null) {
			System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", good.getNumber(), good.getName(), good.getPrice(),
					good.getPress(), good.getAuthor(), good.getLocation(), good.getDate(), good.getTime());
		}else {
			System.out.println("没有查询到结果");
		}
	}

	@Test
	public void TestInsert() throws IOException {
		Goods goods = new Goods();
		goods.setNumber("6902083881085");
		goods.setName("娃哈哈AD钙奶");
		goods.setPrice("4元/瓶");
		goods.setDate("2019年2月8日");
		goods.setAuthor("");
		goods.setPress("");
		goods.setLocation("");
		goods.setTime("20190129114720");
		sqlSession.insert("goodsMapper.insert", goods);
	}

	/**
	 * 
	 * Delete
	 * 
	 * @throws IOException
	 */
	@Test
	public void TestDelete() throws IOException {
		sqlSession.delete("goodsMapper.delete", "6902083881085");
	}

	/**
	 * 
	 * update
	 * 
	 * @param args
	 */

	@Test
	public void TestUpdate() throws IOException {
		Goods goods = new Goods();
		goods.setNumber("6902083881085");
		goods.setName("新娃哈哈AD钙奶");
		goods.setPrice("5元/瓶");
		goods.setDate("2019年2月9日");
		goods.setAuthor("");
		goods.setPress("");
		goods.setLocation("");
		goods.setTime("20190129114720");
		sqlSession.update("goodsMapper.update", goods);
	}

	public static void main(String[] args) {

	}

}
