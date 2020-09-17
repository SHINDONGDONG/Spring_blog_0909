package com.cos.blog_0909.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Lob//大容量
	private String content;
	
	private int count; //カウント
	
	@ManyToOne //user一人が沢山の投稿可能
	@JoinColumn(name = "userId")
	private User user;
	
	//FKではないのでDBにCOLUMNを作成しないでねって //FKではない場合LAZE戦略になるのでEAGERに変更しよう
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)//FKではないことを知らせるReply.classのBOARD 変数をMappedByに代入
	@JsonIgnoreProperties({"board"})
	@OrderBy("id DESC")
	private List<Reply> reply; //1つの投稿に沢山のコメントが書ける
	 
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
}
