package models

import org.joda.time.DateTime

case class Domain(id: Int, domain: String, description: String, created_at: DateTime, updated_at: DateTime, active: Boolean)

case class Alias(id: Int, domain_id: Int, address: String, goto: String, created_at: DateTime, updated_at: DateTime, active: Boolean)
