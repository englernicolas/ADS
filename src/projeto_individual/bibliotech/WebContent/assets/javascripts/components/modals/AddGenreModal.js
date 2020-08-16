import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddGenreModal',
    data: () => ({
        valid: false,
        genre: {
            name: '',
        },
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    mounted() {
        $bus.$on('reset-modal-content', () => {
            this.$refs.form.reset()
        })
    },
    beforeDestoy() {
        $bus.$off('reset-modal-content')
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },
    },
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="genre.name" :rules="requiredMessage" label="Nome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="validate">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
