export default {
    name: 'SearchBox',
    data: () => ({
        searchContent: ''
    }),
    computed: {
        removeRoundRightSide() {
            return `
              border-bottom-right-radius: 0 !important;
              border-top-right-radius: 0 !important;
              overflow: hidden;
            `
        },
        removeRoundLeftSide() {
            return `
              border-bottom-left-radius: 0 !important;
              border-top-left-radius: 0 !important;
              overflow: hidden;
            `
        },
        searchBoxWidth() {
            return `
                width: 350px !important
            `
        },
    }
    ,
    template: /*html*/ `
        <div :style="searchBoxWidth">
            <v-form class="d-flex m-0">
                <v-text-field :style="removeRoundRightSide" v-model="searchContent" color="teal" placeholder="Pesquisar..." hide-details dense filled clearable></v-text-field>
                <v-btn :style="removeRoundLeftSide" color="primary"><v-icon color="white">mdi-magnify</v-icon></v-btn>
            </v-form>            
        </div>`
}
