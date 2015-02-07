package net.carshopping.services.manage.area.impl;import java.util.Arrays;import java.util.Comparator;import java.util.Iterator;import java.util.List;import java.util.Map;import java.util.Map.Entry;import net.carshopping.core.ServersManager;import net.carshopping.services.manage.area.AreaService;import net.carshopping.services.manage.area.bean.Area;import net.carshopping.services.manage.area.dao.AreaDao;import org.apache.commons.lang.StringUtils;public class AreaServiceImpl extends ServersManager<Area> implements		AreaService {	private AreaDao areaDao;	public void setAreaDao(AreaDao areaDao) {		this.areaDao = areaDao;	}	@Override	public void deleteAll() {		areaDao.deleteAll();		}	@Override	public List<Area> getAreaTree(String pid) {		Area area = new Area();		area.setPcode("0");		List<Area> areaList = areaDao.selectList(area);		if(areaList==null || areaList.size()==0){			return null;		}		// 加载子菜单，注意 只加载type为模块级或页面级的		for (int i = 0; i < areaList.size(); i++) {			loadChildrenByPid(areaList.get(i));		}		return areaList;	}		/**	 * 根据父ID加载子节点	 * @param item	 * @param menu	 * @param url	 * @param u	 */	private void loadChildrenByPid(Area area) {		if(StringUtils.isBlank(area.getPcode())){			return;		}		// 加载菜单节点		List<Area> data = areaDao.selectListByPcode(area.getCode());		if (data == null || data.size() == 0) {			return;		}				area.setChildren(data);		// 创建菜单节点		for (int i = 0; i < area.getChildren().size(); i++) {			loadChildrenByPid(area.getChildren().get(i));		}	}		/**	 * 根据菜单ID，删除菜单树勾选的节点。某个非叶子节点即便它下面的所有的叶子节点都被勾选也不会被本次删除操作删除掉，	 * 这样做是为了避免只想删除某个非叶子节点下面的所有子节点	 * 	 * @param ids	 * @param deleteParent	 *            是否级联删除父菜单,在父菜单下的所有子菜单全部勾选的情况下,1:级联删除,-1不级联	 */	@Override	public void deletes2(String ids, String deleteParent) {		String[] idArr = ids.split(",");//		System.out.println(Arrays.toString(idArr));		// 按照从小到大排序		Arrays.sort(idArr, new Comparator<String>() {			public int compare(String o1, String o2) {				int a1 = Integer.parseInt(o1);				int a2 = Integer.parseInt(o2);				if (a1 > a2) {					return 1;				} else if (a1 < a2) {					return -1;				}				return 0;			}		});		Area area = new Area();		if (deleteParent.equals("-1")) {			// 从菜单ID最小的开始删起，避免先把ID大的删除了，倒置ID小的成为了叶子节点而被删除掉			for (int i = 0; i < idArr.length; i++) {				/*				 * 1、菜单下没有子菜单，直接删除 2、菜单下有子菜单，检查所有的子菜单是否全部已经勾选 A)全部勾选，则可以删除。				 * B)没有全部勾选，则不能删除。				 */				area.clear();				area.setPcode(idArr[i]);				if (this.getCount(area) == 0) {					// 指定节点下没有子菜单，删除指定的菜单(叶子)					area.clear();					area.setCode(idArr[i]);					this.delete(area);				}			}		} else if (deleteParent.equals("1")) {			for (int i = idArr.length - 1; i >= 0; i--) {				/*				 * 1、菜单下没有子菜单，直接删除 2、菜单下有子菜单，检查所有的子菜单是否全部已经勾选 A)全部勾选，则可以删除。				 * B)没有全部勾选，则不能删除。				 */				area.clear();				area.setPcode(idArr[i]);				if (this.getCount(area) == 0) {					// 指定节点下没有子菜单，删除指定的菜单(叶子)					area.clear();					area.setCode(idArr[i]);					this.delete(area);				} else {					area.clear();					area.setPcode(idArr[i]);					// 查询指定菜单下的全部子菜单					List<Area> areaList = this.selectList(area);//					System.out.println("find menus:" + menus);					if (areaList != null && areaList.size() > 0) {						if (checkAllContains(idArr, areaList)) {							this.delete(area);//							System.out.println("del menus:" + menu);						}					}				}			}		} else {			throw new NullPointerException("deleteParent:" + deleteParent);		}	}		/**	 * 检查指定的菜单列表是否全部存在于另一个列表中	 * 	 * @param idArr	 *            待删除的菜单列表	 * @param list	 *            被检查的菜单列表	 * @return 全部存在返回true，否则返回false	 */	private boolean checkAllContains(String[] idArr, List<Area> list) {		int n = list.size();		for (int i = 0; i < list.size(); i++) {			for (int j = 0; j < idArr.length; j++) {				if (list.get(i).getId().equals(idArr[j])) {					n--;					break;				}			}		}		// System.out.println("=========="+Arrays.toString(idArr)+",list:"+list+",n:"+n);		return n == 0 ? true : false;	}		public int getCount(Area area) {		return areaDao.getCount(area);	}	@Override	public void initAreaDataToDB(Map<String, Area> map) {		areaDao.deleteAll();		for(Iterator<Entry<String, Area>> it = map.entrySet().iterator();it.hasNext();){			Entry<String, Area> entry = it.next();			Area info = entry.getValue();			areaDao.insert(info);		}			}}