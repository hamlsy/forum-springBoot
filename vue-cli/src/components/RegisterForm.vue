<template>
  <div class="signup-container">
    <h2>회원가입</h2>
    <form @submit.prevent="register">
      <div class="form-group">
        <label for="name">이름</label>
        <input type="text" id="name" v-model="name" required>
      </div>
      <div class="form-group">
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" v-model="nickname" required>
      </div>
      <div class="form-group">
        <label for="userId">아이디</label>
        <input type="text" id="userId" v-model="userId" required>
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div class="button-group">
        <button type="submit">가입하기</button>
        <button type="button" onclick="location.href='/member/login'">로그인</button>
      </div>
    </form>
  </div>
</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
        name: '',
        nickname: '',
        userId: '',
        password: ''
    };
  },
  methods: {
    register(){
      axios.post("/member/register", {
        name:this.name,
        nickname:this.nickname,
        userId:this.userId,
        password:this.password,
      }).then((res) => {
        alert("회원가입에 성공했습니다.")
        window.location = "/login";
        console.log("회원가입에 성공했습니다.", res)
      }).catch ((res) => {
        alert("회원가입에 실패했습니다.")
        console.log("회원가입에 실패했습니다.", res)
      })
    }
  }
};
</script>

<style scoped>
.signup-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f2f2f2;
  border-radius: 5px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.button-group {
  display: flex;
  justify-content: space-between;
}

button {
  width: 48%;
  padding: 10px;
  background-color: #000;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #333;
}
</style>