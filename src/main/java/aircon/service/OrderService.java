package aircon.service;

import aircon.dao.OrderDao;
import aircon.model.ArgumentCantBeNull;
import aircon.model.FieldIsUnchangeable;
import aircon.model.IncorrectOrderStateChange;
import aircon.model.IncorrectValueException;
import aircon.model.Order;
import aircon.model.Order.StateType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class OrderService {
    @Inject
    OrderDao dao;


    Order getById(Long uid) {
        return dao.getByUid(uid);
    }
    
	/**
	 * Отвечает за ввод основных полей заказа. 
	 * Правка основных полей в обход этой функции нежелательна.
	 * @param order Заказ
	 * @param productManufacturerAndModel Наименование производителя и модели устанавливаемого прибора
	 * @param customerName Наименование заказчика
	 * @param targetAddress Адрес, по которому предполагается произвести монтаж
	 * @throws InvalidInputException В случае неверных входных данных
	 * @throws ArgumentCantBeNull В случае если один или несколько переданных параметров имеют значение null
	 * @throws FieldIsUnchangeable В случае если данное поле не может быть изменено при данном состоянии заказа
	 * @throws IncorrectValueException В случае некорректного значения
	 */
	private void setBasicOrderFields(
			Order order,
			String productManufacturerAndModel,
			String customerName, 
			String targetAddress) throws InvalidInputException, ArgumentCantBeNull, FieldIsUnchangeable, IncorrectValueException
	{
		order.setProductManufacturerAndModel(productManufacturerAndModel);
		order.setCustomerName(customerName);
		order.setTargetAddress(targetAddress);		
	}
	
	/**
	 * Создает новый заказ. Присваивает ему статус {@link il.aircon.model.Order.StateType.STATE_NEW}.
	 * @param productManufacturerAndModel Наименование производителя и модели устанавливаемого прибора
	 * @param customerName Наименование заказчика
	 * @param targetAddress Адрес, по которому предполагается произвести монтаж
	 * @return Возвращается объект {@link il.aircon.model.Order}
	 * @throws InvalidInputException В случае неверных входных данных
	 * @throws ArgumentCantBeNull В случае если один или несколько переданных параметров имеют значение null
	 * @throws FieldIsUnchangeable В случае если данное поле не может быть изменено при данном состоянии заказа
	 * @throws IncorrectValueException В случае некорректного значения
	 * @throws IncorrectOrderStateChange В случае если сменить состояние указанным образом невозможно
	 */
	

	public Order CreateNewOrder(
			String productManufacturerAndModel,
			String customerName, 
			String targetAddress) throws InvalidInputException, ArgumentCantBeNull, FieldIsUnchangeable, IncorrectOrderStateChange, IncorrectValueException
	{
		Order res = new Order();
		res.setState(StateType.STATE_NEW);
		setBasicOrderFields(res, productManufacturerAndModel, customerName, targetAddress);
		
		dao.saveOrUpdate(res);
		//dao.
		return res;
	}
	
	
}
