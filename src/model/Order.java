package model;

public class Order {

	private int order_id;// 订单编号
	private Double order_price;// 订单购买单价（图书价格乘以折扣）
	private Double order_total;// 订单总价格
	private int order_amount;// 订单数量
	private String order_remark;// 订单备注
	private int order_book_id;// 图书编号
	private int order_reader_id;// 读者ID

	public Order(Double order_price, Double order_total, int order_amount, String order_remark, int order_book_id,
			int order_reader_id) {
		super();
		this.order_price = order_price;
		this.order_total = order_total;
		this.order_amount = order_amount;
		this.order_remark = order_remark;
		this.order_book_id = order_book_id;
		this.order_reader_id = order_reader_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Double getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}

	public Double getOrder_total() {
		return order_total;
	}

	public void setOrder_total(Double order_total) {
		this.order_total = order_total;
	}

	public int getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}

	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	public int getOrder_book_id() {
		return order_book_id;
	}

	public void setOrder_book_id(int order_book_id) {
		this.order_book_id = order_book_id;
	}

	public int getOrder_reader_id() {
		return order_reader_id;
	}

	public void setOrder_reader_id(int order_reader_id) {
		this.order_reader_id = order_reader_id;
	}

}
