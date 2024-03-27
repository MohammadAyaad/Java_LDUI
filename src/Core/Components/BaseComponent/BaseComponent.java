package Core.Components.BaseComponent;

import java.util.List;

import Core.Components.InitStatus;
import Core.Components.BaseComponent.StructureComponent.StructureComponent;

public class BaseComponent {
	protected BaseComponent parent;
	protected List<BaseComponent> children;
	protected Exception exception;
	protected InitStatus status;
	
	
	
	public void addChild(BaseComponent component)
	{
		component.parent = this;
		children.add(component);
		
	}
	public void removeChild(int index)
	{
		children.remove(index);
	}
	public void clear()
	{
		children.clear();
	}
	public List<BaseComponent> getChildren()
	{
		return this.children;
	}
	public BaseComponent getParent()
	{
		return this.parent;
	}
	
}
