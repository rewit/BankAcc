package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.model.BankBalanceVO;

public class BankService {

	/*
	 * balance.txt 파일을 읽어서 계좌정보를 balanceList에 추가하는 메서드를 작성
	 */
	List<BankBalanceVO> balanceList;
	Scanner scan;
	FileReader fileReader;
	BufferedReader buffer;

	public BankService(String fileName) throws FileNotFoundException {
		scan = new Scanner(System.in);
		balanceList = new ArrayList<BankBalanceVO>();
		fileReader = new FileReader(fileName);
		buffer = new BufferedReader(fileReader);
		// 생성자에서 생성해야 다른곳에서 오류가 덜 함
	}

	public void readBalance() throws IOException {
		// reader 생성
		// 반복위해 while문

		String reader = "";
		while (true) {
			reader = buffer.readLine();// reader 를
			if (reader == null)
				break;

			String[] banks = reader.split(":");

			BankBalanceVO vo = new BankBalanceVO(banks[0], Integer.valueOf(banks[1]), banks[2]);

			vo.setAcc(banks[0]);
			vo.setBalance(Integer.valueOf(banks[1]));
			vo.setDate(banks[2]);
			
			balanceList.add(vo); 

		}

	}

	public BankBalanceVO pickAcc(String accNum) {

		//String acc = "0001";
		/*
		 * balanceList에서 계좌번호 0001인 데이터를 찾고 그 계좌에 현잔액을 콘솔에 표시
		 */
		int index = 0;
		int bankSize = balanceList.size();
		BankBalanceVO vo = null;
////		for(BankBalanceVO vo : balanceList)	{		//for 안에 if는 1번만  없을 경우 확인은 for 밖에서
		for (index = 0; index < bankSize; index++) {
			vo = balanceList.get(index);
			if (vo.getAcc().equals(accNum)) {
				// System.out.println(vo.getBalance());
				return vo;
			}
		}
		return null;
	}//pickAcc end
	
	/*
	 * 계좌번호로 pickAcc()로부터 vo값을 추출해오고
	 * balance값과 money값을 더하여 
	 * vo의 balance에 저장하고 콘솔에 보여주는 코드
	 */
	public void inputMoney(String acc, int money) {
		
//		for(BankBalanceVO vo : balanceList) {
//			System.out.println(vo);
//		}
		
		BankBalanceVO vo = pickAcc(acc); //pickAcc한테 acc를 뱅크 밸런스 vo에 받음
		int bal = vo.getBalance();
		vo.setBalance(bal + money);
		
		//현재 컴퓨터날짜값을 가져오기
		//java 1.7 이하에서 지금도 사용하는 코드
		Date date = 
				new Date(System.currentTimeMillis()); //1.8이상에선 사용하지 x , 현 업체들은 1.7버전이라 많이 사용
							//1.8이상에서는 System.currentTimeMillis() 안넣어두됨 
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		String curDate = sf.format(date);
		
		vo.setDate(curDate);
		
		//Java 1.8(8)이상에서 사용하는 새로운 날짜
		LocalDate localDate = LocalDate.now();
		vo.setDate(localDate.toString()); 	//실무에선 아직 안씀
		
		System.out.println("======================================");
		System.out.println(vo);
		System.out.println("======================================");
	

//		System.out.println("===============================================");
//		for(BankBalanceVO v : balanceList) {
//			System.out.println(v);
//		}
		
		
	
	
	}
	
	public void outputMoney(String acc, int money) {
		
//		for(BankBalanceVO vo : balanceList) {
//			System.out.println(vo);
//		}
		
		BankBalanceVO vo = pickAcc(acc); //pickAcc한테 acc를 뱅크 밸런스 vo에 받음
		int bal = vo.getBalance();
		
		//출금일 경우는 현잔액을 검사해서
		//출금액보다 크면 출금 금지
		if(bal < money) {
			System.out.println("잔액부족");
			return;
		}
		vo.setBalance(bal - money);
		
		//현재 컴퓨터날짜값을 가져오기
		//java 1.7 이하에서 지금도 사용하는 코드
		Date date = 
				new Date(System.currentTimeMillis()); //1.8이상에선 사용하지 x , 현 업체들은 1.7버전이라 많이 사용
							//1.8이상에서는 System.currentTimeMillis() 안넣어두됨 
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		String curDate = sf.format(date);
		
		vo.setDate(curDate);
		
		//Java 1.8(8)이상에서 사용하는 새로운 날짜
		LocalDate localDate = LocalDate.now();
		vo.setDate(localDate.toString()); 	//실무에선 아직 안씀
		
		System.out.println("======================================");
		System.out.println(vo);
		System.out.println("======================================");
	

//		System.out.println("===============================================");
//		for(BankBalanceVO v : balanceList) {
//			System.out.println(v);
//		}
		
		
	
	
	}
	
	
	
	public int selectMenu() {
	System.out.println("===============================");
	System.out.println("1. 입금   2.출금   -9.종료");
	System.out.println("-------------------------------");
	System.out.print("업무선택");
	String strMenu = scan.nextLine();
	
	int intMenu = 0;
	try {
		intMenu = Integer.valueOf(strMenu);
	} catch (Exception e) {
		//오류 무시
	}
	return intMenu;
	
	}

}
