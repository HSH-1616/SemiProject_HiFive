package com.semi.member.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.service.MemberService;

@WebServlet("/sendEmail")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendEmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inputEmail = request.getParameter("inputEmail"); // 입력받은 이메일
		String subject = "[Semi-프로젝트] 회원 가입 이메일 인증번호"; // 제목

		String fromEmail = "semiproject@semi.com"; // 보내는 사람으로 표시될 이메일 (이메일 따라서 안될수도 있음)
		String fromUsername = "관리자"; // 보내는 사람 이름
		String toEmail = inputEmail; // 받는사람, 콤마(,)로 여러개 나열 가능

		// 구글 이메일을 이용한 메일 보내기 (SMTP)
		// 1. 구글 계정 생성(기존 이메일 사용해도됨)
		// 2. 계정 -> 보안 설정 진행
		// 1) 2단계 인증 추가
		// 2) 앱 비밀번호 생성(메일, 서버컴퓨터 OS) -> 저장해두기

		final String smtpEmail = "bogdudhkd123@gmail.com"; // 이메일
		final String password = "uregzrrzxkpfkcld"; // 발급 받은 비밀번호

		Properties p = System.getProperties(); 
		p.setProperty("mail.transport.protocol", "smtp");
		/* p.setProperty("mail.host", "smtp.gmail.com"); */
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		/* p.put("mail.smtp.quitwait", "false"); */
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2");
		p.put("mail.smtp.socketFactory.port", "587");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		/* p.put("mail.smtp.socketFactory.fallback", "true"); */

		  Session session = Session.getDefaultInstance(p);

		try {

			/*
			 * Session session = Session.getDefaultInstance(p, new
			 * javax.mail.Authenticator() { protected PasswordAuthentication
			 * getPasswordAuthentication() { return new PasswordAuthentication(smtpEmail,
			 * password); } });
			 */

			MimeMessage msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(fromEmail, fromUsername)); // 송신자(보내는 사람) 지정
			msg.addRecipient(RecipientType.TO, new InternetAddress(toEmail)); // 수신자(받는사람) 지정
			msg.setSubject(subject); // 이메일 제목 지정
			
			// 메일 제목

			// 인증번호 6자리 생성
			String cNumber = "";
			Random rnd = new Random();
			for (int i = 0; i < 6; i++) {

				int sel1 = (int) (Math.random() * 3); // 0:숫자 / 1,2:영어

				if (sel1 == 0) {

					int num = (int) (Math.random() * 10); // 0~9
					cNumber += num;

				} else {

					char ch = (char) (Math.random() * 26 + 65); // A~Z

					int sel2 = (int) (Math.random() * 2); // 0:소문자 / 1:대문자

					if (sel2 == 0) {
						ch = (char) (ch + ('a' - 'A')); // 대문자로 변경
					}

					cNumber += ch;
				}
			}

			String AuthenticationKey = cNumber.toString();
			System.out.println(AuthenticationKey);

			StringBuffer sb = new StringBuffer(); // 가변성 문자열 저장 객체
			sb.append("<h3>[Semi-프로젝트] 회원 가입 인증 번호입니다.</h3>\n");
			sb.append("<h3>인증 번호 : <span style='color:red'>" + cNumber + "</span></h3>\n");
			msg.setText("인증 번호는 ["+ cNumber +"] 입니다");
			
			Transport t = session.getTransport("smtp");
			t.connect(smtpEmail, password);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			
			int result = new MemberService().insertCertification(inputEmail, cNumber);
			response.getWriter().print(result);

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
			response.setStatus(500);
			response.getWriter().print(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
