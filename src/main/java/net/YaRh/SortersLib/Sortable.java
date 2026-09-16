package net.YaRh.SortersLib;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;

public class Sortable<O> {
	
	private static int compareString(String a, String b) {
		return Integer.compare(a.length(), b.length());
	}
	
	private final O value;
	
	private int amount = 1;
	
	public Sortable(O value) {
		this.value = value;
	}
	
	/**
	 * Compares the values of another {@linkplain Sortable} with its own
	 *
	 * @return Result relative to own value<p>
	 *     {@code < -> -1}
	 *     {@code == -> 0}
	 *     {@code < -> 1}
	 *
	 * @throws ExecutionControl.NotImplementedException When the comparison of a value type is not yet supported
	 */
	public int compare(Sortable<O> pOther) throws ExecutionControl.NotImplementedException {
		O other = pOther.getValue();
		if (value instanceof Integer) return Integer.compare(((Integer) value), ((Integer) other));
		if (value instanceof String) return Sortable.compareString(((String) value), ((String) other));
		if (value instanceof Double) return Double.compare(((Double) value), ((Double) other));
		if (value instanceof Float) return Float.compare(((Float) value), ((Float) other));
		
		throw new ExecutionControl.NotImplementedException("Comparisons of type " + value.getClass() + " not supported yet");
	}
	
	public int add() {
		return add(1);
	}
	public int add(int pAmount) {
		this.amount += pAmount;
		return amount;
	}
	
	public int remove() {
		return remove(1);
	}
	public int remove(int pAmount) {
		this.amount -= pAmount;
		return amount;
	}
	
	public List<O> get() {
		List<O> list = new ArrayList<>();
		for (int i = 0; i < amount; i++) list.add(value);
		return list;
	}
	
	private O getValue() {
		return value;
	}
}