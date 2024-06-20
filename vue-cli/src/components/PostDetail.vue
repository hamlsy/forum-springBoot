<template>
  <div class="post-detail-container">
    <nav class="navbar">
      <ul>
        <li><a href="/post/list">Home</a></li>
        <li><a href="/member/register">회원가입</a></li>
        <li><a href="/member/login">로그인</a></li>
        <li><a href="/member/logout">로그아웃</a></li>
      </ul>
    </nav>

    <div class="post-content">
      <div class="post-header">
        <h2 class="post-title">{{ post.subject }}</h2>
        <div class="post-meta">
          <span class="post-author">작성자: {{ post.nickname }}</span>
          <div class="button-group">
            <button @click="editPost">수정</button>
            <button @click="deletePost">삭제</button>
          </div>
        </div>
      </div>
      <p class="post-body">{{ post.content }}</p>
    </div>

    <div class="comment-section">
      <h3>댓글</h3>
      <div class="comment-form">
        <textarea v-model="newComment" placeholder="댓글을 입력하세요"></textarea>
        <button @click="addComment">등록</button>
      </div>
      <ul class="comment-list">
        <li v-for="(comment, index) in comments" :key="index">
          <span class="comment-author">{{ comment.author }}</span>
          <p class="comment-text">{{ comment.text }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      post: {
        postId:'',
        subject: '',
        nickname: '',
        content: ''
      },
      newComment: '',
      comments: []
    };
  },
  created() {
    this.getPostDetail();
  },
  methods: {
    getPostDetail(){
      axios.get("/post/" + this.$route.params.id)
          .then((res) => {
            this.post.postId = res.data.postId;
            this.post.subject = res.data.subject;
            this.post.content = res.data.content;
            this.post.nickname = res.data.nickname;
            console.log("게시글 id: " + this.postId, res)
          })
          .catch((res) => {
            console.log("게시글 로드 실패", res);
          })
    },
    editPost() {
      // 게시글 수정 로직 추가
      console.log('게시글 수정');
    },
    deletePost() {
      // 게시글 삭제 로직 추가
      console.log('게시글 삭제');
    },
    addComment() {
      // 댓글 등록 로직 추가
      console.log('댓글 등록:', this.newComment);
      this.newComment = '';
    }
  }
};
</script>

<style scoped>
.post-detail-container {
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

.post-content {
  background-color: #f2f2f2;
  padding: 20px;
  border-radius: 5px;
  margin-bottom: 20px;
}

.post-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.post-title {
  font-size: 24px;
  font-weight: bold;
}

.post-meta {
  display: flex;
  align-items: center;
}

.post-author {
  margin-right: 20px;
}

.button-group {
  display: flex;
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

.post-body {
  margin-top: 100px;
  margin-bottom: 20px;
  min-height: 200px;
}

.comment-section {
  background-color: #f2f2f2;
  padding: 20px;
  border-radius: 5px;
}

.comment-form {
  margin-bottom: 20px;
  display: flex;
  align-items: flex-end;
}

textarea {
  flex-grow: 1;
  height: 80px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

.comment-form button {
  margin-left: 10px;
}

.comment-list {
  list-style-type: none;
  padding: 0;
}

.comment-list li {
  display: flex;
  margin-bottom: 10px;
  padding: 10px;
  background-color: #fff;
  border-radius: 4px;
}

.comment-author {
  font-weight: bold;
  margin-right: 10px;
}

.comment-text {
  flex-grow: 1;
}
</style>