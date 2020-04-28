Vue.component('portfolio-update', {
  props: ['portfolio'],
  data: () => ({
    buttonDisabled: false,
    error: null
  }),
  template: `
  <div class="modal fade" id="portfolioModalUpdate" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">Update Portfolio</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="title" class="col-form-label">Title:</label>
            <input v-model="portfolio.title" type="text" class="form-control" id="title" maxlength="255">
          </div>
          <div class="form-group">
            <label for="twitterUsername" class="col-form-label">Twitter username:</label>
            <input v-model="portfolio.twitterUsername" type="text" class="form-control" id="twitterUsername" maxlength="255">
          </div>
          <div class="form-group">
            <label for="photo" class="col-form-label">Image URL:</label>
            <input v-model="portfolio.photo" type="text" class="form-control" id="photo" maxlength="255">
          </div>
          <div class="form-group">
            <label for="description" class="col-form-label">Description:</label>
            <textarea v-model="portfolio.description" class="form-control" id="description" maxlength="255"></textarea>
          </div>
        </form>
        <div v-if="error">
            <div v-if="error" class="alert alert-danger" role="alert">
              {{ error }}
            </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button v-on:click="updatePortfolio" :disabled="buttonDisabled"  type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
    `
  ,
  methods: {
    updatePortfolio() {
      console.log("Update portfolio id:", this.portfolio.id)
      this.error = null;
      this.buttonDisabled = true;

      let requestBody = JSON.stringify({
        photo: this.portfolio.photo,
        title: this.portfolio.title,
        description: this.portfolio.description,
        twitterUsername: this.portfolio.twitterUsername,
      });

      let resStatus = 0;
      fetch(`/portfolio/${this.portfolio.id}`, {
        method: 'PUT',
        body: requestBody,
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      }).then(response => {
        resStatus = response.status
        return response.json()
      }).then(response => {
        switch (resStatus) {
          case 200:
            alert("Portfolio updated!");
            break
          default:
            this.error = response.message;
            break
        }
      })
      .catch(err => {
        console.error('Error to call update', err);
      });

      this.buttonDisabled = false;
    }
  }
})
;