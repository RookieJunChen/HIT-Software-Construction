package relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个relationshipbetweenco的具体实现
 * 
 * @author junbaba
 *
 */
public class Basicrelationshipbetweenco<L,E> implements relationshipbetweenco<L, E>
{
	protected final L c;
	protected final List<Socialtie<E>> contacts = new ArrayList<>();
	
	// Abstraction function:
	// c对应中心点物体，contacts列表中每一个元素对应与中心点物体存在联系的一个轨道物体
	
	// Representation invariant:
	// contacts != null
	
	// Safety from rep exposure:
	// 通过protected使其它非子类中无法得知本类中的rep,并且get方法所得到的返回值均仅只能查看无法修改.
	
	// TODO constructor
	public Basicrelationshipbetweenco(L c) 
	{
		this.c = c;
	}
	
	// TODO checkRep
	public void checkRep()
    {
    	assert contacts != null;
    	
    }
	
	@Override
	public L getc()
	{
		return c;
	}
	
	
	@Override
	public List<Socialtie<E>> getrel()
	{
		return contacts;
	}
	
	
	@Override
	public void addconnection(E object , Number intimacy)
	{
		Socialtie<E> s = new Socialtie<E>(object, intimacy);
		contacts.add(s);
		checkRep();
	}
}
