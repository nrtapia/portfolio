Vue.component('form-search', {
  template: `
      <form class="form-inline">
        <label class="sr-only" for="id">Portfolio ID</label>
        <input name="id" v-model.number="id" type="text" class="form-control mb-2 mr-sm-2" id="id"
               placeholder="Portfolio ID">
        <button v-on:click="$emit('find-by-id-event', id)" type="button"
                class="btn btn-primary mb-2">
          Search
        </button>
      </form>
    `
  ,
  data: () => ({
    id: ''
  })
});