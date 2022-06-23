<template>
<div class="text-left">
  <div class="row">
    <div class="col-sm-12">
      <h1>◎ 파일 확장자 차단</h1>
      <hr/>
      <h3>파일확장자에 따라 특정 형식의 파일을 첨부하거나 전송하지 못하도록 제한</h3>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-1 fw-bold">고정 확장자</div>
    <div class="col-sm-9">
      <span class="form-check form-check-inline" v-for="item in fixes" :key="item.extension">
        <input class="fixes-input" type="checkbox" :id="item.extension" :value="item.extension" :checked="item.isEnabled" @click="updateFixed(item)">
        <label class="fixes-label" :for="item.extension">&nbsp;{{item.extension}}</label>
      </span>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-1 fw-bold">커스텀 확장자</div>
    <div class="col-sm-5">
      <b-input-group>
        <b-form-input v-model.trim="custom" maxLength="20"></b-form-input>
        <b-input-group-append>
          <b-button variant="primary" @click="addCustom()">+추가</b-button>
        </b-input-group-append>
      </b-input-group>
    </div>
    <div class="mt-1" style="">
      <div class="offset-sm-1 col-sm-5 border rounded" style="min-height: 200px;">
        <div>{{customs.length}} / 200</div>
        <div>
          <span v-for="item in customs">
            {{item}} <b-button size="sm" @click="deleteCustom(item)">X</b-button>
          </span>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FileExtensionBlock',
  data: () => {
    return {
      fixes: [],
      selectedFixes: [],
      customs: [],
      custom: null,
    }
  },
  async mounted() {
    await this.load();
  },
  props: {
    msg: String
  },
  methods: {
    async load() {
      const list = (await axios.get('/api/fileExtensionBlocks')).data;
      console.log(list);
      let fixes = [];
      let selectedFixes = [];
      let customs = [];
      list.forEach(item => {
        if (item.isFixed) {
          fixes.push(item);
          if (item.isEnabled) {
            selectedFixes.push(item.extension)
          }
        } else {
          customs.push(item.extension);
        }
      })
      this.fixes = fixes;
      this.selectedFixes = selectedFixes;
      this.customs = customs;
    },
    updateFixed(item) {
      console.log(JSON.stringify(item))
      if (!item.isFixed) {
        return;
      }
      const fixed = {
        isFixed: item.isFixed,
        isEnabled: !item.isEnabled
      };

      axios.put(`/api/fileExtensionBlocks/${item.extension}`, fixed)
      .then(async () => {
        await this.load();
      }, this.error)
    },
    addCustom() {
      const custom = {
        extension: this.custom,
        isFixed: false,
        isEnabled: true
      };
      axios.post("/api/fileExtensionBlocks", custom)
      .then(async () => {
        await this.load();
        this.custom = null;
      }, this.error);
    },
    deleteCustom(extension) {
      axios.delete(`/api/fileExtensionBlocks/${extension}`)
      .then(async () => {
        await this.load();
      }, this.error);
    },
    error(err) {
      console.log(err)
      alert(err)
    }
  }
}
</script>

<style scoped>

</style>