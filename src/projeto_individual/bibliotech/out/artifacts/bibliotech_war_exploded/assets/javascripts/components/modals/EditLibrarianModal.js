import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'EditLibrarianModal',
    props: {
        schools: Array,
        genders: Array
    },
    data: () => ({
        valid: true,
        editing: false,
        librarian: {},
        emailRules: [
            v => /.+@.+\..+/.test(v) || 'Email inválido',
        ],
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        cpfRules: [
            v => /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/.test(v) || 'CPF inválido',
        ],
        menu: false,
    }),
    computed: {
        getUserId() {
            return this.$route.query.id
        }
    },
    mounted() {
        this.getLibrarian()
        $bus.$on('load-content', () => {
            this.getLibrarian()
        })
    },
    watch: {
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },
    methods: {
        save (date) {
            this.$refs.menu.save(date)
        },
        validate () {
            this.$refs.form.validate()
        },

        async getLibrarian() {
            this.loadingUser = true

            await axios.get(`/librarian/getById?id=${this.getUserId}`)
                .then((response) => {
                    this.librarian = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por estudantes"
                })
                .finally(() => {
                    this.loadingUser = false
                })
        },

        async editLibrarian() {
            this.validate()

            if (this.valid) {
                this.editing = true

                const librarian = this.librarian

                await axios.put('/librarian/edit', librarian)
                    .then(() => {
                        $bus.$emit('refresh-librarians')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar editar estudante"
                    })
                    .finally(() => {
                        this.editing = false
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
                            v-model="librarian.firstName" :rules="requiredMessage" label="Nome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            v-model="librarian.lastName" :rules="requiredMessage" label="Sobrenome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="librarian.email" :rules="emailRules" label="E-mail" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            type="password" v-model="librarian.password" :rules="requiredMessage" label="Senha" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                        >
                            <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                    v-model="librarian.birthDate"
                                    label="Data de Nascimento"
                                    readonly
                                    hint="YYYY-MM-DD format"
                                    persistent-hint
                                    v-bind="attrs"
                                    v-on="on"
                                    outlined
                                ></v-text-field>
                            </template>
                            <v-date-picker
                                ref="picker"
                                v-model="librarian.birthDate"
                                :max="new Date().toISOString().substr(0, 10)"
                                min="1950-01-01"
                                @change="save"
                            ></v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col>    
                        <v-text-field v-mask="['###.###.###-##']"
                            v-model="librarian.cpf" :rules="requiredMessage && cpfRules" label="CPF" color="teal" outlined required
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>    
                        <v-select
                            v-model="librarian.genderId" :items="genders" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Sexo" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-select
                            v-model="librarian.schoolId" :items="schools" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Escola" color="teal" required outlined
                        ></v-select>
                    </v-col>
                </v-row>    
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="editStudent" v-if="!editing">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
