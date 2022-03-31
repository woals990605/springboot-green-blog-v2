      // 1. 이벤트 리스너
      $("#btn-join").click(()=>{
        join();
      });

      
         // button에 이벤트 리스너 달기
    $("#btn-login").click(()=>{
        login();
    });

      $("#btn-update").click(()=>{
        update();
      });

      // 2. 기능



      // 회원가입 요청 함수
      let join =async() =>{
        // (1) username, password, email, addr을 찾아서 오브젝트로 만든다.
        let joinDto ={
          username : $("#username").val(),
          password : $("#password").val(),
          email : $("#email").val(),
          addr : $("#addr").val()
        }
        // (2) JSON으로 변환한다. (통신의 표준이 JSON이니까)
        // (3) fetch 요청한다.
        let response = await fetch ("/join", {
          method : "POST",
          body : JSON.stringify(joinDto),
          headers : {
            "Content-Type": "application/json; charset=utf-8",
          }
        });
        // (4) 회원가입이 잘되면 알림창 띄우고 로그인 페이지로 이동한다.
        let responseParse = await response.json();

        if (responseParse.code == 1){
          alert("회원가입 완료");
          location.href = "/loginForm"
        }else{
          alert("회원가입 실패")
        }
      }

    // 회원가입 요청 함수
    // username, password를 자바스크립에서 찾기
    let login = async()=>{

      // checkbox의 체크여부를 제이쿼리에서 확인하는 법
      let checked = $("#remember").is(":checked");

        let loginDto={
            username : $("#username").val(),
            password : $("#password").val(),
            remember : checked ? "on" : "off"
        }

        // json으로 변환해서 fetch 요청 /api/login POST
        let response = await fetch("/login", {
            method : "POST",
            body : JSON.stringify(loginDto),
            headers : {
                "Content-Type": "application/json; charset=utf-8",
            }
        });

        // 로그인 잘되면 알람창 띄우고 메인 페이지로 이동한다.
        let responseParse = await response.json();

        if (responseParse.code == 1){
            alert("로그인 완료");
            location.href = "/"
        }else{
            alert("로그인 실패")
        }
      }

      // 회원정보 수정 함수
      async function update(){
        let updateDto= {
          password: $("#password").val(),
          email: $("#email").val(),
          addr: $("#addr").val()
        }
        console.log(updateDto);

        let id = $("#id").val();
        console.log(id);

        let response = await fetch(`/s/api/user/${id}`,{
          method: "PUT",
        body: JSON.stringify(updateDto),
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        });
        let responseParse = await response.json();
        console.log(responseParse);

        if (responseParse.code == 1) {
          alert("회원수정완료");
           location.href = `/s/user/${id}`;
        } else {
           alert('회원수정실패');
    }

    
      }