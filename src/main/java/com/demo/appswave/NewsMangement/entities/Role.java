package com.demo.appswave.NewsMangement.entities;

import com.demo.appswave.NewsMangement.enumeration.ERole;
import jakarta.persistence.*;

@Entity
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        public Role() {

        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Role(ERole name) {
                this.name = name;
        }

        public ERole getName() {
                return name;
        }

        public void setName(ERole name) {
                this.name = name;
        }

        @Enumerated(EnumType.STRING)
        @Column(length = 20)
        private ERole name;

    }
