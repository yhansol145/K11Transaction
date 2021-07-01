package com.kosmo.k11transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import transaction.TicketDAO;
import transaction.TicketDTO;
import transaction.TicketTplDAO;

@Controller
public class TransactionController {
	
	/*
	1. 트랜잭션 매니저를 활용한 트랜잭션 처리
		: servlet-context.xml에서 미리 생성한 DAO를 자동주입 받아 사용한다.
	 */
//	private TicketDAO dao;
//	@Autowired
//	public void setDao(TicketDAO dao) {
//		this.dao = dao;
//		System.out.println("@Autowired=>TicketDAO 주입성공"); 
//	}
//	//티켓 구매 페이지
//	@RequestMapping("/transaction/buyTicketMain.do")
//	public String buyTicketMain() {
//		
//		return "08Transaction/buyTicketMain";
//	}
//	
//	@RequestMapping("/transaction/buyTicketAction.do")
//	public String buyTicketAction(TicketDTO ticketDTO,
//			/*
//			클라이언트가 전송한 폼값을 커맨드 객체를 통해 한번에
//			받아 DAO로 전달한다.
//			 */
//			Model model) {
//		dao.buyTicket(ticketDTO);
//		model.addAttribute("ticketInfo", ticketDTO);
//		return "08Transaction/buyTicketAction";
//	}
	
	/****************************
		트랜잭션 매니저와 템플릿은 동시에 사용할 수 없으므로
		2단계에서는 1단계의 모든 부분을 주석으로 처리한 후 진행합니다.
	****************************/
	
	/*
	2. 트랜잭션 템플릿을 활용한 처리
	 */
	//자동주입시 setter를 사용하지 않아도 됨. 멤버변수에 직접 적용할 수 있음.
	private TicketTplDAO daoTpl;
	@Autowired
	public void setDaoTpl(TicketTplDAO daoTpl) {
		this.daoTpl = daoTpl;
	}
	
	//구매페이지
	@RequestMapping("/transaction/buyTicketTpl.do")
	public String buyTicketTpl() {
		
		return "08Transaction/buyTicketTpl";
	}
	
	//구매처리
	@RequestMapping("/transaction/buyTicketTplAction.do")
	public String buyTicketTplAction(TicketDTO ticketDTO,
			Model model) {
		
		//폼값을 저장한 커맨드 객체를 매개변수로 구매 메소드 호출
		boolean isBool = daoTpl.buyTicket(ticketDTO);
		if(isBool==true) {
			//정상처리된 경우 모든 작업은 commit된다.
			model.addAttribute("successOrFail",
					"티켓구매가 정상처리 되었습니다.");
		}
		else {
			//오류가 발생한 경우에는 모든 작업이 rollback된다.
			model.addAttribute("successOrFail",
					"티켓구매가 취소되었습니다. 다시 시도해 주세요.");
		}
		
		//뷰로 전달할 데이터 저장
		model.addAttribute("ticketInfo", ticketDTO);
		
		return "08Transaction/buyTicketTplAction";
	}

}
