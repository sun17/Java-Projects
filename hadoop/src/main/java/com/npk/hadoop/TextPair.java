package com.npk.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextPair implements WritableComparable<TextPair>{

	private Text first;
	private Text second;
	
	public void set(Text first, Text second){
		this.first = first;
		this.second = second;
	}
	
	public TextPair(){
		set(new Text(), new Text());
	}

	public TextPair(Text first, Text second){
		set(first, second);
	}
	
	public TextPair(String first , String second){
		set (new Text(first), new Text(second));
	}
	
	
	public Text getFirst(){ 
		return first; 
	}
	
	public Text getSecond(){
		return second;
	}
	
	
	@Override
	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);
	}

	@Override
	public int compareTo(TextPair textpair) {
		
		int cmp = first.compareTo(textpair.first);
		if (cmp != 0)
			return cmp;
		
		return second.compareTo(textpair.second);
	}
	
	@Override
	public boolean equals (Object o){
		
		if (o instanceof TextPair){
			TextPair textpair = (TextPair) o;
			return first.equals(textpair.first) && second.equals(textpair.second);
		}else
			return false;
	}
	
	@Override
	public int hashCode(){
	
		return first.hashCode() * 31 + second.hashCode();
	}
	
	@Override
	public String toString(){
		return first + "\t" + second;
	}

}
