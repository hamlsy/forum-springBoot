<template>
  <div class="post-form-container">
    <nav class="navbar">
      <ul class="nav-links">
        <li><a href="/post/list">Home</a></li>
        <li><a href="/member/register">회원가입</a></li>
        <li><a href="/member/login">로그인</a></li>
        <li><a href="/member/logout">로그아웃</a></li>
      </ul>
    </nav>

    <div class="post-form">
      <h2>게시글 작성</h2>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="subject">제목</label>
          <input type="text" id="subject" v-model="post.subject" required>
        </div>
        <div class="form-group">
          <label for="content">내용</label>
          <textarea id="content" v-model="post.content" required></textarea>
        </div>
        <div class="button-group">
          <button type="button" @click="cancelForm">취소</button>
          <button type="submit">저장</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      post: {
        subject: '',
        content: ''
      }
    };
  },
  methods: {
    submitForm() {
      axios.post("/post/upload")
          .then((res) => {
            alert("게시글을 업로드했습니다.");
            window.location.href = "/post/list";
            console.log("게시글을 업로드했습니다.",res);
          })
          .catch((res) => {
            console.log("게시글 업로드에 실패했습니다.",res);
          })

      // 게시글 저장 로직 추가
      console.log(this.post);
      // 저장 후 게시글 목록 페이지로 이동
      this.$router.push('/post/list');
    },
    cancelForm() {
      // 취소 버튼 클릭 시 이전 페이지로 이동
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.post-form-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}


.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #333;
  color: #fff;
  padding: 10px;
  z-index: 999;
}
.navbar ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: flex-end;
}

.navbar li {
  margin-left: 20px;
}

.navbar a {
  color: white;
  text-decoration: none;
}

.post-form {
  background-color: #f2f2f2;
  padding: 20px;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

input[type="text"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

textarea {
  height: 200px;
}

.button-group {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  background-color: #000;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 10px;
}

button:hover {
  background-color: #333;
}
</style>