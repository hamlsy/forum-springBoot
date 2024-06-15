<template>
  <div>
    <h2>로그인</h2>
    <form @submit.prevent="login">
      <div>
        <label for="username">아이디:</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    login() {
      // 로그인 로직 구현
      console.log('로그인 버튼 클릭');
      console.log('아이디:', this.username);
      console.log('비밀번호:', this.password);
    }
  }
};
</script>