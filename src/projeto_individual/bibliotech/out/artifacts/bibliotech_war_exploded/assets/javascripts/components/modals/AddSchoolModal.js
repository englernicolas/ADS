import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddSchoolModal',
    data: () => ({
        valid: false,
        creating: false,
        school: {
            name: '',
        },
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    mounted() {
        if(this.$refs.form) {
            $bus.$on('reset-modal-content', () => {
                this.school = {
                    name: '',
                }
            })   
        }
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },

        async createSchool() {
            this.validate()

            if (this.valid) {
                this.creating = true

                const school = this.school

                await axios.post('/school/create', school)
                    .then(() => {
                        $bus.$emit('refresh-schools')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar escola"
                    })
                    .finally(() => {
                        this.creating = false
                    })
            }
        },
    },
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="school.name" :rules="requiredMessage" label="Nome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createSchool">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
