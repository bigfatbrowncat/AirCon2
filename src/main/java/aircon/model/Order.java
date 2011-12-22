package aircon.model;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.*;

/*
@Entity
//@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String address;


    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
*/

/**
 * Заказ
 */
@Entity
public class Order
{
	public enum StateType
	{
		
		STATE_NEW(0) 				   ,	//				вновь созданная заявка
		STATE_AFTER_INSPECTION(1) 	   ,	//				после проведения осмотра предполагаемого места установки
		STATE_COMPLETE(2)		 	   ,	//				после монтажа сплит-системы
		STATE_CANCELLED(3);		 	    	//				заявка отменена
		
		private int value;

		StateType(int value) { this.value = value; }
	}
	
	// Служебные поля
    
	/**
	 * Уникальный идентификатор заказа
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
	
	/**
	 * Момент создания заявки
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date created;												

	/**
	 * Момент последнего изменения заявки
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;
	
	/**
	 * Статус выполнения заявки
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StateType state = StateType.STATE_NEW;

	// Основные поля (могут быть заполнены в состоянии new и after inspection)
	
	/**
	 * <p>Данные о модели устанавливаемой сплит-системы</p>
	 * <p>Является основным полем</p>
	 */
	@Column(length=255, nullable=false)
	private String productManufacturerAndModel; 				
	
	/**
	 * <p>Наименование заказчика (ФИО для физического лица, либо полное наименование для организации)</p>
	 * <p>Является основным полем</p>
	 */
	@Column(length=255, nullable=false)
	private String customerName;

	/**
	 * <p>Адрес проведения работ</p>
	 * <p>Является основным полем</p>
	 */
	@Column(length=255, nullable=false)
	private String targetAddress; 				

	// Дополнительные поля (могут быть заполнены только в состоянии after inspection)

	/**
	 * <p>Длина трубопроводной магистрали между внутренним и внешним блоками</p>
	 * <p>Является дополнительным полем</p>
	 */
	@Column
	private Float pipeLineLength;						// [метры]		длина трубопроводной магистрали между внутренним и внешним блоками

	/**
	 * <p>Количество дозаправленного хдадагента (если пришлось дозаправлять)</p>
	 * <p>Является дополнительным полем</p>
	 */
	@Column
	private Float additionalCoolantAmount;

	/**
	 * <p>Необходимость установки дренажой помпы</p>
	 * <p>Является дополнительным полем</p>
	 */
	@Column
	private Boolean pumpNeeded;		

	// Автоматические поля (рассчитываются системой, пользовательский ввод невозможен)
	
	/**
	 * <p>Стоимость всех работ по заказу</p>
	 * <p>Рассчитывается системой</p>
	 */
	@Column
	private BigDecimal fullCost;				
	
	public Order()
	{
		this.lastModified = new Date();
		this.created = lastModified;
	}
	
	public Date getCreated() {
		return created;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) throws FieldIsUnchangeable {
		if (state == StateType.STATE_COMPLETE || state == StateType.STATE_CANCELLED)
			throw new FieldIsUnchangeable("targetAddress");
		this.targetAddress = targetAddress;
		this.lastModified = new Date();
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) throws FieldIsUnchangeable, IncorrectValueException, ArgumentCantBeNull {
		if (state == StateType.STATE_COMPLETE || state == StateType.STATE_CANCELLED)
			throw new FieldIsUnchangeable("customerName");
		if (customerName == null) throw new ArgumentCantBeNull("customerName");
		if (customerName.length() > 255) throw new IncorrectValueException("customerName");
		this.customerName = customerName;
		this.lastModified = new Date();
	}
	
	public String getProductManufacturerAndModel() {
		return productManufacturerAndModel;
	}
	public void setProductManufacturerAndModel(
			String productManufacturerAndModel) throws FieldIsUnchangeable, IncorrectValueException, ArgumentCantBeNull {
		if (state == StateType.STATE_COMPLETE || state == StateType.STATE_CANCELLED)
			throw new FieldIsUnchangeable("productManufacturerAndModel");
		if (productManufacturerAndModel == null) throw new ArgumentCantBeNull("productManufacturerAndModel");
		if (productManufacturerAndModel.length() > 255) throw new IncorrectValueException("productManufacturerAndModel");
		this.productManufacturerAndModel = productManufacturerAndModel;
		this.lastModified = new Date();
	}
	
	public Float getPipeLineLength() {
		return pipeLineLength;
	}
	public void setPipeLineLength(Float pipeLineLength) throws IncorrectValueException, FieldIsUnchangeable, ArgumentCantBeNull {
		if (state == StateType.STATE_NEW || state == StateType.STATE_COMPLETE || state == StateType.STATE_CANCELLED)
			throw new FieldIsUnchangeable("pipeLineLength");
		if (pipeLineLength == null) throw new ArgumentCantBeNull("pipeLineLength");
		if (pipeLineLength <= 0) throw new IncorrectValueException("pipeLineLength");
		this.pipeLineLength = pipeLineLength;
		this.lastModified = new Date();
	}
	
	public Float getAdditionalCoolantAmount() {
		return additionalCoolantAmount;
	}
	public void setAdditionalCoolantAmount(Float additionalCoolantAmount) throws IncorrectValueException, FieldIsUnchangeable, ArgumentCantBeNull {
		if (state == StateType.STATE_NEW || state == StateType.STATE_COMPLETE || state == StateType.STATE_CANCELLED)
			throw new FieldIsUnchangeable("additionalCoolantAmount");
		if (additionalCoolantAmount == null) throw new ArgumentCantBeNull("additionalCoolantAmount");
		if (additionalCoolantAmount < 0) throw new IncorrectValueException("additionalCoolantAmount");
		this.additionalCoolantAmount = additionalCoolantAmount;
		this.lastModified = new Date();
	}
	
	public Boolean getPumpNeeded() {
		return pumpNeeded;
	}
	public void setPumpNeeded(Boolean pumpNeeded) throws FieldIsUnchangeable, ArgumentCantBeNull {
		if (state == StateType.STATE_NEW || state == StateType.STATE_COMPLETE || state == StateType.STATE_CANCELLED)
			throw new FieldIsUnchangeable("pumpNeeded");
		if (pumpNeeded == null) throw new ArgumentCantBeNull("pumpNeeded");
		this.pumpNeeded = pumpNeeded;
	}
	
	public BigDecimal getFullCost() {
		return fullCost;
	}
	public void setFullCost(BigDecimal fullCost) throws IncorrectValueException, FieldIsUnchangeable, ArgumentCantBeNull {
		if (state == StateType.STATE_NEW || state == StateType.STATE_COMPLETE || state == StateType.STATE_CANCELLED)
			throw new FieldIsUnchangeable("fullCost");
		if (fullCost == null) throw new ArgumentCantBeNull("fullCost");
		if (fullCost.compareTo(new BigDecimal(0)) < 0) throw new IncorrectValueException("fullCost can not be negative");
		this.fullCost = fullCost;
	}
	
	public StateType getState() {
		return state;
	}
	
	public void setState(StateType state) throws IncorrectOrderStateChange, ArgumentCantBeNull {
		if (state == null) throw new ArgumentCantBeNull("state");
		if (this.state == state) return;
		
		switch (this.state)
		{
		case STATE_NEW:
			if (state == StateType.STATE_COMPLETE) throw new IncorrectOrderStateChange(this.state, state);
			break;
		case STATE_AFTER_INSPECTION:
			if (state == StateType.STATE_NEW) throw new IncorrectOrderStateChange(this.state, state);
			break;
		case STATE_CANCELLED:
			if (state == StateType.STATE_COMPLETE) throw new IncorrectOrderStateChange(this.state, state);
			break;
		case STATE_COMPLETE:
			throw new IncorrectOrderStateChange(this.state, state);
		}
		
		this.state = state;
		this.lastModified = new Date();
	}
	public Long getUid() {
		return uid;
	}

}
