
<template>
    <div>
      <form @submit = "login">
<!--        <div>-->
<!--          <div class="alert alert-danger">-->
<!--            ID 또는 비밀번호를 확인해 주세요.-->
<!--          </div>-->
<!--        </div>-->
        <div class="mb-3">
          <label for="userId" class="form-label">아이디</label>
          <input type="text" name="userId" id="userId" class="form-control">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">비밀번호</label>
          <input type="password" name="password" id="password" class="form-control">
        </div>
        <button type="submit" class="btn btn-dark">로그인</button>
      </form>
      <button type="submit" class="btn btn-dark">회원가입</button>

    </div>
</template>
<style scoped>

</style>
<script>
import axios from "axios";

export default {
  data() {
    return{
      userId: '',
      password: ''
    }
  },
  methods: {
    login() {
      // 로그인 로직 구현
      axios.post('/login', {
        userId: this.userId,
        password: this.password
      })
    }
  }
};
</script>